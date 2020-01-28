# Spring clound AWS - SQS and SNS 

### Create SQS
1. Enter Queue name select the default option and then click on `Quick-Create Queue`
<img width="1133" alt="Screen Shot 2020-01-28 at 2 14 11 PM" src="https://user-images.githubusercontent.com/870715/73296842-79c64600-41d8-11ea-8ea0-30523e2754d6.png">

Now go to Queue console and copy the `ARN`, Need this while configuring `Topic` Subscription

<img width="359" alt="Screen Shot 2020-01-28 at 2 15 41 PM" src="https://user-images.githubusercontent.com/870715/73297004-c14cd200-41d8-11ea-836b-56dd7776b611.png">
### Create SNS
<img width="1241" alt="Screen Shot 2020-01-28 at 2 09 14 PM" src="https://user-images.githubusercontent.com/870715/73296502-d07f5000-41d7-11ea-93e7-b340ba3042b9.png">

1. Enter a topic name and click next
2. Select all default and click on `Create topic`

<img width="1095" alt="Screen Shot 2020-01-28 at 2 11 02 PM" src="https://user-images.githubusercontent.com/870715/73296596-0a505680-41d8-11ea-9baa-7c8f3b3f96db.png">

### Create subscription to that Topic
In this case, we are subscribing `AmazonSQS` protocol and using the queue `ARN` created above
<img width="260" alt="Screen Shot 2020-01-28 at 2 17 38 PM" src="https://user-images.githubusercontent.com/870715/73297150-0bce4e80-41d9-11ea-91fc-612005ba0398.png">

### Permissions

Before forwarding the message, we will have to add the permission to receive messages


<img width="1067" alt="Screen Shot 2020-01-28 at 2 23 30 PM" src="https://user-images.githubusercontent.com/870715/73297565-cbbb9b80-41d9-11ea-9c57-225d1eb21a88.png">



