# Spring Cloud Vault Demo


## Vault Docker image

create a docker image with `vault-config.json`, and this can be build using docker-compose.

Start the vault

 `docker-compose up --build`
 
This will start Vault and Vault UI

<img width="1447" alt="Screen Shot 2020-01-17 at 11 19 51 AM" src="https://user-images.githubusercontent.com/870715/72627993-7058f600-391b-11ea-8292-d4f1e265cc0a.png">

As you can see, Vault is not initialized yet.

#### Initialize Vault:

Can run the following command inside vault container

```bash 
vault operator init
```
this provides an unsealed keys and root token

<img width="558" alt="Screen Shot 2020-01-17 at 11 29 27 AM" src="https://user-images.githubusercontent.com/870715/72628614-a5b21380-391c-11ea-948c-8d03fc51e5a5.png">

now time to unseal keys, Need to run this command three time with different unseal keys
```bash
vault operator unseal <unseal Key>
```

<img width="592" alt="Screen Shot 2020-01-17 at 11 32 41 AM" src="https://user-images.githubusercontent.com/870715/72628832-2709a600-391d-11ea-99f4-d959fc4acfb3.png">

Now its time to authenticate with token

```bash
vault login <token>
```
<img width="553" alt="Screen Shot 2020-01-17 at 11 38 12 AM" src="https://user-images.githubusercontent.com/870715/72629230-e8282000-391d-11ea-80eb-454b907df2d8.png">

Once you done with above steps go back to to `Vault UI`, and you will see something like this and ready to login with token

<img width="1266" alt="Screen Shot 2020-01-17 at 11 37 54 AM" src="https://user-images.githubusercontent.com/870715/72629306-0ee65680-391e-11ea-83d0-0a55c6d6b170.png">

All these above steps can be added to scripts and execute along with docker vault startup.




