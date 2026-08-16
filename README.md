# PropertyWale

Cloud-ready property search & management portal (Spring Boot + Thymeleaf), inspired by
MagicBricks / 99acres / Housing.com / NoBroker.

## What's in this build

This is **stage 1 of the project — the public property search + details vertical slice**,
built end-to-end (entity → repository → service → controller → view) so it runs immediately
with sample data. Later stages (see `Roadmap`) add auth, favourites, enquiry persistence,
and the full admin module described in the original spec.

### Working right now
- Home page: hero search bar, category grid, featured & latest listings
- Property search (`/properties`) with filters: city, locality, category, budget range, keyword — server-side pagination
- Property details page: image gallery, key facts, amenities, embedded Google Map, enquiry form (UI wired, backend stubbed — see Roadmap)
- About Us / Contact Us pages
- Global 404 / 500 error pages
- Sample data auto-seeded on startup (10 categories, 9 properties across Pune/Mumbai/Bengaluru)
- Dev profile: zero-setup H2 in-memory DB
- Prod profile: MySQL/PostgreSQL via environment variables
- Docker + docker-compose (app + MySQL)

## Run locally (dev profile, H2, no setup needed)

Requires JDK 21 and Maven 3.9+.

```bash
mvn spring-boot:run
```

Then open http://localhost:8080

H2 console (dev only): http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:propertywale`
- User: `sa`, no password

## Run with Docker Compose (prod profile, MySQL)

```bash
docker compose up --build
```

This builds the app image and starts a MySQL 8 container alongside it. The app connects
using the `DB_URL` / `DB_USERNAME` / `DB_PASSWORD` env vars defined in `docker-compose.yml`.

## Deploying

The `prod` profile reads its datasource entirely from environment variables, so it drops
straight into:
- **AWS EC2**: run the Docker image directly, or `java -jar propertywale.jar` behind a reverse proxy, pointing `DB_URL` at RDS (MySQL/Postgres).
- **Azure App Service**: deploy the container image; set `DB_URL`/`DB_USERNAME`/`DB_PASSWORD` as app settings.
- **Render / Railway**: both support Dockerfile-based deploys — attach their managed Postgres and set `DB_URL=jdbc:postgresql://...`.

Set `SPRING_PROFILES_ACTIVE=prod` in all of the above.

## Project layout

```
src/main/java/com/propertywale/
  entity/          Property, Category, PropertyImage, PropertyStatus
  repository/       PropertyRepository, CategoryRepository (+ specification/ for dynamic search)
  dto/             PropertySearchCriteria, PropertySummaryDto, PropertyDetailsDto
  service/         PropertyService, CategoryService (+ impl/)
  controller/      HomeController, PropertyController
  config/          DataLoader (sample data seeding)
  exception/       ResourceNotFoundException, GlobalExceptionHandler
src/main/resources/
  templates/       Thymeleaf views + fragments/ (navbar, footer, property-card)
  static/          css/, js/, images/
  application*.properties
```

## Roadmap (not yet built)

- Spring Security: registration/login, `ROLE_USER` / `ROLE_ADMIN`, BCrypt
- Favourites / Save for later
- Enquiry persistence + admin inbox (form UI already in place on the details page)
- Full admin dashboard: property/category/user/enquiry CRUD, stats
- Contact Us submission persistence
- Multi-image upload for admin-added properties
- Google Maps API key wiring for a richer map experience (currently uses the no-key embed URL)
