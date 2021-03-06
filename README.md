## User Directory Management Service with simple CRUD operations

When you land on the home page, you will see a search bar with search box, find button and active-inactive toggle.

# Features

## Default Behaviour On Search Page
On load, toggle will be **active (green color)** and loads all active users.

We can see all Inactive users as well, by just doing **toggle to inactive** (grey color)

## Search
Search will be performed with minimum of **4 characters**

Type name of user for e.g. `Magnate` in search bar fetches all records with name contains `Magnate` with **default country** filter as `Singapore`

If you know which country users you're looking for you can combine to type **Country** with simple delimiter `,`

Type `Magnate, Singapore`

Type `Magnate, Malaysia` as well to see the difference

The same search behaviour is available for Inactive users as well, just **toggle to inactive** (grey color)

Also, all the records are sorted by First Name of the user

## Soft & Perm Delete
**Active User Records** can be soft-deleted, by clicking button `-`. Soft-deleted records are now **Inactive Users**

**Inactive User Records** can be permanently deleted, by clicking button `x`

## More Options
Swagger-UI is available
Through Swagger Client or Postman, we can also run
- Adding/ Updating new user
- Updating address of specific user
- Search active/ inactive user by `Id`

## Create an Azure Database for PostgreSQL - Single Server using the AzureCLI

<https://docs.microsoft.com/en-us/azure/postgresql/quickstart-create-server-database-azure-cli>

**And Note Down**

- AZ_POSTGRES_SERVER=azsc-postgres-server
- AZ_LOCAL_IP_ADDRESS=183.83.240.53
- AZ_POSTGRESQL_ADMIN_USER=postgres
- AZ_POSTGRESQL_ADMIN_PASSWORD=Im4mPalo#
- AZ_DATABASE_NAME=azsc-demo


# TODO
- [x] Spring Boot Kotlin Application
- [x] Deploy to Azure Spring Cloud
- [x] Azure Database for PostgreSQL Server
- [x] ~~Move Static Resources to Azure Web - App Service~~
- [x] Move Static Resources to Gateway (Simplicity Sake)
- [ ] Azure Active Directory for Authentication to Gateway
- [ ] Gradle Build with Multiple Profiles


