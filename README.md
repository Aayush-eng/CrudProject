**Postman Testing Guide**

Okay, now that we've updated the code with the `@JsonManagedReference` and `@JsonBackReference` annotations, let's go through how to test each endpoint using Postman.

**Important Notes:**

* **Base URL:** Assume your application is running locally at `http://localhost:8080`. Adjust the port if needed.
* **Content-Type:** For `POST` and `PUT` requests that send JSON data, make sure to set the `Content-Type` header to `application/json` in Postman.

**1. Testing User Operations**

* **Create a User**

    * **URL:** `http://localhost:8080/users`
    * **Method:** `POST`
    * **Body (JSON):**

    ```json
    {
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "city": "New York",
      "age": 30
    }
    ```

    * **Expected Result:** A new user is created. The response will be a JSON object representing the created user, *without* the nested `nominees` or `claims` due to the `@JsonManagedReference`.

* **Get All Users**

    * **URL:** `http://localhost:8080/users`
    * **Method:** `GET`
    * **Body:** None
    * **Expected Result:** A list of users. Again, the `nominees` and `claims` will be present but not fully expanded to avoid recursion.

* **Get User by ID**

    * **URL:** `http://localhost:8080/users/{userId}` (e.g., `http://localhost:8080/users/1`)
    * **Method:** `GET`
    * **Body:** None
    * **Expected Result:** The user with the specified ID.

* **Update User**

    * **URL:** `http://localhost:8080/users/{userId}` (e.g., `http://localhost:8080/users/1`)
    * **Method:** `PUT`
    * **Body (JSON):**

    ```json
    {
      "userId": 1,
      "firstName": "Updated John",
      "lastName": "Updated Doe",
      "email": "updated.john@example.com",
      "city": "Los Angeles",
      "age": 31
    }
    ```

    * **Important:** Include the `userId` in the body, and it *must* match the `{userId}` in the URL.
    * **Expected Result:** The updated user.

* **Delete User**

    * **URL:** `http://localhost:8080/users/{userId}` (e.g., `http://localhost:8080/users/1`)
    * **Method:** `DELETE`
    * **Body:** None
    * **Expected Result:** A 204 No Content response.

* **Get User with Claims**

    * **URL:** `http://localhost:8080/users/{userId}/claims` (e.g., `http://localhost:8080/users/1/claims`)
    * **Method:** `GET`
    * **Body:** None
    * **Expected Result:** The user object, including its associated claims (but without infinite recursion).

* **Add User with Nominees**

    * **URL:** `http://localhost:8080/users/add`
    * **Method:** `POST`
    * **Body (JSON):**

    ```json
    {
      "user": {
        "firstName": "Alice",
        "lastName": "Smith",
        "email": "alice.smith@example.com",
        "city": "Chicago",
        "age": 25
      },
      "nominees": [
        {
          "name": "Nominee 1"
        },
        {
          "name": "Nominee 2"
        }
      ]
    }
    ```

    * **Expected Result:** A new user is created, and the nominees are associated with that user. The response will show the user and the associated nominees (without infinite nesting).

**2. Testing Nominee Operations**

* **Add Nominee**

    * **URL:** `http://localhost:8080/nominees/add-nominee`
    * **Method:** `POST`
    * **Body (JSON):**

    ```json
    {
      "name": "Jane Doe"
    }
    ```

    * **Expected Result:** A new nominee is created.

* **Get All Nominees**

    * **URL:** `http://localhost:8080/nominees/all-nominee-list`
    * **Method:** `GET`
    * **Body:** None
    * **Expected Result:** A list of all nominees.

* **Get Nominee by ID**

    * **URL:** `http://localhost:8080/nominees/get-nominee/{id}` (e.g., `http://localhost:8080/nominees/get-nominee/1`)
    * **Method:** `GET`
    * **Body:** None
    * **Expected Result:** The nominee with the given ID, or a 404 if not found.

* **Delete Nominee**

    * **URL:** `http://localhost:8080/nominees/delete/{id}` (e.g., `http://localhost:8080/nominees/delete/1`)
    * **Method:** `DELETE`
    * **Body:** None
    * **Expected Result:** A 204 No Content response.

* **Update Nominee**

    * **URL:** `http://localhost:8080/nominees/{id}` (e.g., `http://localhost:8080/nominees/1`)
    * **Method:** `PUT`
    * **Body (JSON):**

    ```json
    {
      "nomineeId": 1,
      "name": "Updated Jane Doe"
    }
    ```

    * **Important:** Include the `nomineeId` in the body, matching the `{id}` in the URL.
    * **Expected Result:** The updated nominee.

**3. Testing Claim Operations**

* **Get All Claims**

    * **URL:** `http://localhost:8080/claims`
    * **Method:** `GET`
    * **Body:** None
    * **Expected Result:** A list of all claims.

* **Get Claim by ID**

    * **URL:** `http://localhost:8080/claims/{id}` (e.g., `http://localhost:8080/claims/1`)
    * **Method:** `GET`
    * **Body:** None
    * **Expected Result:** The claim with the given ID, or a 404 if not found.

* **Create Claim**

    * **URL:** `http://localhost:8080/claims`
    * **Method:** `POST`
    * **Body (JSON):**

    ```json
    {
      "claimAmount": 1000.00,
      "claimStatus": "Pending",
      "user": {
        "userId": 1  // The ID of the user (must exist)
      }
    }
    ```

    * **Important:** Provide the `userId` of an *existing* user.
    * **Expected Result:** A new claim is created.

* **Update Claim**

    * **URL:** `http://localhost:8080/claims/{id}` (e.g., `http://localhost:8080/claims/1`)
    * **Method:** `PUT`
    * **Body (JSON):**

    ```json
    {
      "claimId": 1,
      "claimAmount": 1200.00,
      "claimStatus": "Approved",
      "user": {
        "userId": 1  // The ID of the user (must exist)
      }
    }
    ```

    * **Important:** Include the `claimId` and the `user` with a valid `userId`.
    * **Expected Result:** The updated claim.

* **Delete Claim**

    * **URL:** `http://localhost:8080/claims/{id}` (e.g., `http://localhost:8080/claims/1`)
    * **Method:** `DELETE`
    * **Body:** None
    * **Expected Result:** A 204 No Content response.

This is the complete text. Just copy and paste it into your preferred document editor, and you can then save it as a Word file, PDF, or any other format you need.

Flow-Chart

  +-----------------+     HTTP Request     +--------------------+     Service Logic     +--------------------+     Database     +-----------------+
   |  Client (e.g.,  |--------------------->|   Controller     |--------------------->|      Service       |--------------------->|   Repository   |--------------------->|   Database     |
   |     Postman)    |                     | (UserController)   |  (UserService)       |   (UserRepository)  |     (MySQL)     |
   +-----------------+                     +--------------------+                     +--------------------+                     +-----------------+
                                                                                         ^                                          |
                                                                                         | HTTP Response                              |
                                                                                         +--------------------+                     |
                                                                                         |   Controller     |<---------------------|
                                                                                         | (UserController)   |                     |
                                                                                         +--------------------+                     |
                                                                                               |<--------------------------------------|
                                                                                               |
                                                                                         +-----------------+
                                                                                         |  Client (e.g.,  |
                                                                                         |     Postman)    |
                                                                                         +-----------------+
