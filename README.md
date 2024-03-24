# Counter API

A simple counter API demo project

## Table of Contents

- [Getting Started](#getting-started)
  - [with Docker](#with-docker)
  - [within IDE](#installation)
- [Usage](#usage)
- [Testing](#testing)

[Back to top](#table-of-contents)

## Getting Started

### with Docker

```text
requirements:
- docker (or any compatible substitute)
```

[Back to top](#table-of-contents)

### with IDE

```text
requirements:
- java 21
- docker (DB cofigured with it, so it is still required)
- preferable IDE
```

[Back to top](#table-of-contents)

## API Calls (expandable)

basic-route - `/api/v1/counters`

<details open>
  <summary>get all counters</summary>
  
   ##### `GET` `/basic-route`
    Returns list of all counters with all properties
  
</details>
<details>
  <summary>get counter</summary>
  
  ##### `GET` `/{id}`
    Returns counter by `id` with all properties
  
</details>
<details>
  <summary>create counter</summary>
  
  ##### `POST` `/new`
    Creates new counter and returns it
  
</details>
<details>
  <summary>increment</summary>
  
  ##### `POST` `/inc/{id}`
    Increments counter by 1 and returns it
  
</details>
<details>
  <summary>decrement</summary>
  
  ##### `POST` `/dec/{id}`
    Decrements counter by 1 and returns it
  
</details>
<details>
  <summary>update value</summary>
  
  ##### `POST` `/update/{id}`
    Adds/subtracts specified amount from counter's value and returns it
  
</details>
<details>
  <summary>set value</summary>
  
  ##### `PATCH` `/set/{id}`
    Sets counter's current value to specified amount and returns it
  
</details>
<details>
  <summary>set top limit</summary>
  
  ##### `PATCH` `/set_top/{id}`
    Sets counter's top limit and returns it
  
</details>
<details>
  <summary>set bottom limit</summary>
  
  ##### `PATCH` `/set_bot/{id}`
    Sets counter's bottom limit and returns it
  
</details>
<details>
  <summary>update counter</summary>
  
  ##### `PUT` `/{id}`
    Allows to update every counter's property and returns it
  
</details>
<details>
  <summary>delete counter</summary>
  
  ##### `DELETE` `/{id}`
    Deletes counter
  
</details>

[Back to top](#table-of-contents)

## Usage

Works just like any other API.
There is [postman collection](https://github.com/Ziryt/Counterapp/blob/main/counters.postman_collection.json) in the project for a quick start


[Back to top](#table-of-contents)
