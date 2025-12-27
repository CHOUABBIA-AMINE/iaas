# Railway Deployment Guide

This guide provides step-by-step instructions for deploying the IaaS application to Railway.

## Prerequisites

- GitHub account
- Railway account (sign up at [railway.app](https://railway.app))
- This repository connected to your GitHub account

## Architecture

- **Application:** Spring Boot 4.0.1 (Java 17)
- **Database:** MySQL 8.0+
- **Build Tool:** Maven
- **Port:** 8080

## Deployment Steps

### 1. Create Railway Project

1. Go to [Railway Dashboard](https://railway.app/dashboard)
2. Click "New Project"
3. Select "Deploy from GitHub repo"
4. Choose `CHOUABBIA-AMINE/iaas` repository
5. Click "Deploy Now"

### 2. Add MySQL Database

1. In your Railway project, click "+ New"
2. Select "Database" → "Add MySQL"
3. Railway will automatically create the MySQL service and set environment variables

### 3. Configure Environment Variables

In your Railway project dashboard, go to your application service and add these variables:

#### Required Variables

| Variable | Value | Description |
|----------|-------|-------------|
| `SPRING_PROFILES_ACTIVE` | `railway` | Activates the Railway configuration profile |
| `JWT_SECRET` | `<your-secure-secret>` | Generate a strong random string (min 64 chars) |
| `MYSQLDB_DATABASE` | `iaas_db` | Database name (Railway auto-sets other MySQL vars) |

#### Optional Variables

| Variable | Value | Description |
|----------|-------|-------------|
| `CORS_ALLOWED_ORIGINS` | `https://your-frontend.com` | Your frontend domain (comma-separated) |
| `PORT` | `8080` | Application port (Railway sets this automatically) |

#### MySQL Variables (Auto-configured by Railway)

These are automatically set when you add the MySQL service:
- `MYSQLDB_HOST`
- `MYSQLDB_PORT`
- `MYSQLDB_USER`
- `MYSQLDB_PASSWORD`

### 4. Generate JWT Secret

Generate a secure JWT secret using one of these methods:

**Option 1: OpenSSL (Linux/Mac)**
```bash
openssl rand -base64 64
```

**Option 2: Node.js**
```bash
node -e "console.log(require('crypto').randomBytes(64).toString('base64'))"
```

**Option 3: Online Generator**
Use: https://generate-random.org/api-token-generator

### 5. Database Schema Setup

The application uses `spring.jpa.hibernate.ddl-auto=update`, so tables will be created automatically on first deployment.

**Optional:** If you want to import the sample data from `iaas_db.sql`:

1. In Railway dashboard, click on MySQL service
2. Go to "Data" tab
3. Connect using provided credentials
4. Import `src/main/resources/iaas_db.sql`

OR use Railway CLI:
```bash
railway run mysql -u root -p < src/main/resources/iaas_db.sql
```

### 6. Deploy

Railway will automatically deploy when you push to the main branch:

```bash
git add .
git commit -m "Configure for Railway deployment"
git push origin main
```

Monitor deployment in Railway dashboard under "Deployments" tab.

### 7. Access Your Application

Once deployed, Railway provides a public URL:
```
https://iaas-production-xxxx.up.railway.app
```

Your API will be accessible at:
```
https://iaas-production-xxxx.up.railway.app/iaas/api
```

## Testing the Deployment

### Health Check
```bash
curl https://your-railway-url.railway.app/iaas/api/actuator/health
```

Expected response:
```json
{
  "status": "UP"
}
```

### API Endpoints

Base URL: `https://your-railway-url.railway.app/iaas/api`

Test available endpoints according to your API documentation.

## Configuration Files

- `src/main/resources/application-railway.properties` - Railway-specific configuration
- `nixpacks.toml` - Railway build configuration
- `pom.xml` - Maven dependencies and build configuration

## Important Notes

### File Storage

⚠️ **Railway uses ephemeral storage.** Uploaded files are stored in `/tmp/uploads` and will be deleted on each redeployment.

**Solutions for persistent storage:**
- AWS S3
- Cloudinary
- DigitalOcean Spaces
- Google Cloud Storage

Update `file.upload-dir` and `app.file.upload-dir` in `application-railway.properties` to use external storage.

### Database Backups

Railway doesn't provide automatic backups for the free tier. Consider:
- Upgrading to paid plan for automated backups
- Implementing custom backup scripts
- Using Railway CLI to export database regularly

### Environment-Specific Settings

- **Local:** Uses `application.properties` (localhost MySQL)
- **Railway:** Uses `application-railway.properties` (Railway MySQL)

Ensure `SPRING_PROFILES_ACTIVE=railway` is set in Railway environment variables.

## Troubleshooting

### Application Won't Start

1. **Check build logs** in Railway dashboard
2. **Verify Java version:** Ensure Railway uses Java 17
3. **Check environment variables:** All required vars must be set
4. **Database connection:** Verify MySQL service is running

### Database Connection Errors

```
Communications link failure
```

**Solution:**
- Ensure MySQL service is running
- Check `MYSQLDB_*` environment variables are set
- Verify `SPRING_PROFILES_ACTIVE=railway`

### Build Failures

```
Failed to execute goal
```

**Solution:**
- Check `pom.xml` syntax
- Verify Maven wrapper permissions:
  ```bash
  git update-index --chmod=+x mvnw
  git commit -m "Fix mvnw permissions"
  git push
  ```

### Port Binding Issues

```
Port already in use
```

**Solution:**
- Railway sets `PORT` environment variable automatically
- Ensure `application-railway.properties` uses `${PORT:8080}`

### CORS Errors

```
CORS policy: No 'Access-Control-Allow-Origin' header
```

**Solution:**
- Set `CORS_ALLOWED_ORIGINS` environment variable
- Include your frontend domain
- Update `application-railway.properties` if needed

## Monitoring & Logs

### View Logs

In Railway dashboard:
1. Click on your application service
2. Go to "Logs" tab
3. Real-time logs will appear

### Health Monitoring

Spring Boot Actuator endpoints:
- Health: `/iaas/api/actuator/health`
- Info: `/iaas/api/actuator/info`
- Metrics: `/iaas/api/actuator/metrics`

### Custom Alerts

Configure Railway webhooks for deployment notifications:
1. Project Settings → Webhooks
2. Add webhook URL (Slack, Discord, etc.)

## Scaling

### Vertical Scaling

1. Go to Project Settings
2. Select your service
3. Change resource allocation (paid plans)

### Horizontal Scaling

Railway supports horizontal scaling on paid plans:
- Configure load balancing
- Use Railway's built-in service replication

## Security Checklist

- ✅ Set strong `JWT_SECRET` (min 64 characters)
- ✅ Enable HTTPS (automatic on Railway)
- ✅ Configure CORS properly
- ✅ Use environment variables for sensitive data
- ✅ Regularly update dependencies
- ✅ Enable database backups
- ✅ Review Spring Security configuration

## Resources

- [Railway Documentation](https://docs.railway.app/)
- [Spring Boot on Railway](https://docs.railway.app/guides/java)
- [MySQL on Railway](https://docs.railway.app/databases/mysql)
- [Railway CLI](https://docs.railway.app/develop/cli)

## Support

For issues specific to this deployment:
1. Check Railway logs for error details
2. Review this guide's troubleshooting section
3. Consult Spring Boot documentation
4. Contact Railway support for platform issues

## Next Steps

1. ✅ Deploy to Railway
2. ⬜ Set up CI/CD pipeline
3. ⬜ Configure custom domain
4. ⬜ Implement external file storage
5. ⬜ Set up monitoring and alerts
6. ⬜ Configure database backups
7. ⬜ Deploy frontend application
8. ⬜ Set up staging environment

---

**Deployment Date:** 2025-12-27
**Last Updated:** 2025-12-27
