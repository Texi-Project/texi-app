# Getting Started
1. Create a MySql database called texi, and use the password in the properties file.

2. Run the sql file data.sql in the resources folder to load the default data

3. Install RabbitMq and create the exchange and queues bellow with their binding
   1. exchange: texiPostUnhealthy 
   2. queue: texiPostUnhealthy
   
You are all set.

For additional features like notifications you shall need this step
which enables web sockets on rabbitMq server.

4. Enable RabbitMQ Web MQTT Plugin
/usr/local/sbin/rabbitmq-plugins enable rabbitmq_web_mqtt

[Tutorial is here] (https://www.rabbitmq.com/web-mqtt.html)