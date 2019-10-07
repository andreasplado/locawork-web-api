#futumap

# Run: 
```mvn spring-boot:run```

#Connect to db:
```heroku pg:psql postgresql-pointy-79328 --app futumap```

##Adding Posgre SQL extensions:
```CREATE EXTENSION earthdistance;```

#Update or create with Flyway
### change application.properties
```spring.jpa.hibernate.ddl-auto=create```

#View server logs
```heroku logs --tail -a futumap```
