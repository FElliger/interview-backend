# Example Backend for Products

## Summary
- service reads data from XML and JSON files
- service exposes REST APIs:
  - GET /v1/products -> show all products without prices
  - GET /v1/products/<id> -> show a specific product will pricing information
  - GET /v1/products/<id>/prices/<unit> -> get price for product unit

## Potential Future Work
- pagination to support larger data sets
- system tests on API level, e.g., using Postman
- authorization
