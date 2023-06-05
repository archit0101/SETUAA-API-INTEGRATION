# SETUAA-API-INTEGRATION

## Problem
I have built an end-to-end demo using the SETU Account Aggregator API where:

1. I established a consent handshake (create and approve)
2. I created a data session
3. I fetched sample data 

For this I used the SETU AA sandbox environment (setup info is below). Their docs are here -> https://setu.co/data/account-aggregator. 

## Setu Sandbox Setup
We have a test FIU account in the sandbox environment (“bridge”) of the AA, Setu. I have done handshaking with their API using the client_id and client_secret to complete consent and fetch the information of a user. 

Refer the doc for Postman collection.

Authentication keys
These keys have to be used for authenticating API calls between your systems and Setu AA
client_id -> 255d0b6c-492d-44cf-8581-e9494c7b0914 (select text to view credentials)
client_secret -> 9c2fc756-3d17-46d6-b28d-be4d71953e83 (select text to view credentials)
