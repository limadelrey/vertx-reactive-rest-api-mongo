FROM mongo

COPY init.json /init.json
CMD mongoimport --host db --username root --password example --authenticationDatabase admin --db book-store --collection books --type json --file /init.json --jsonArray