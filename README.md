Basic test web app for creation of a JCR node in a modeshape instance
===================================================================

Configurations
--------------

This test project comes with a set of configuration files which can be activated by changing the value in the init() method of the RepositoryWrapper

### modeshape-config1.json:
A modeshape configuration using a binary store of type cache, with transactions enabled, and query enabled

### modeshape-config2.json:
A modeshape configuration using a binary store of type file, with transactions disabled, and query enabled

### modeshape-config3.json:
A modeshape configuration using a binary store of type file, with transactions disabled, and query disabled


