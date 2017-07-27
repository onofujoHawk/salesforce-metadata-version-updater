# Salesforce Metadata version Updater

**_@author_** Onofrio Falco 

**_@date_** Jul 2017

#### Questo script esegue la modifica delle ApiVersion dei metadati Salesforce.
Il principale scopo di questo script è quello di leggere tutto il contenuto delle directory _classes_, 
_pages_, _triggers_ e _components_, ed andare a modificare la versione contenuta tra i tag `<apiVersion></apiVersion>`.
