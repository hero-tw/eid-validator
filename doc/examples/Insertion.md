
To insert an identity record into the database:

```
POST /identity
```
```json
{
    "name": {
      "prefix": "Rev.",
      "givenName": "Martin",
      "secondaryName": "Luther",
      "surname": "King",
      "postfix": "Jr."
    },
    "dateOfBirth": "1929-01-15",
    "addresses": [
      {
        "number": "501",
        "street": "Auburn Ave NE",
        "unit": null,
        "additional": null,
        "cityRegion": "Atlanta",
        "stateProvince": "Georgia",
        "postalCode": "30312",
        "countryCode": "US",
        "startDate": "1929-01-15",
        "endDate": "1947-06-15"
      }
    ],
    "stateIds": [
      {
        "issuer": "SSA",
        "number": "555-55-5555",
        "expiration": null
      }
    ]
}
```