# Proxy ( Grabber-Checker )

# Features

- Grabbing Proxies

- Checking Proxies "NEW" If there is any issues open new issue

- Pinging System

<img src="https://j.top4top.io/p_1635s36z01.png"/>


# Countries

- Enter the code of the country EX : US , IQ , EG

# Grab Types

- Http

- Socks4

- Socks5

# Check Types

- Http

- Socks4

- Socks5

- ALL ( all.txt ) i mean

# API Call

```java
API api = new API();

api.GrabProxies(county,type);

// If want all countries api.GrabProxies("all",type)

api.CheckProxies(type);

// If u want to get the number of loaded proxies and good proxies

System.out.println("Loaded" + api.proxies.size());
System.out.println("Good proxies: " + api.goodproxies.size());




```

# Changelogs

- Added Proxy Checking feature

- Added Ping System

- Fixed Write only one good proxy
