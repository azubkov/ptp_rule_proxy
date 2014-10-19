ptp_rule__proxy
=========

point-to-point-proxy with rules.
Able to listen HTTP calls to some port. 
Proxies them to specified remote machine. 
Shows all the request-responses in the log.

using xml config file you can replace string in attributes/content.


Running:
azoo.com.ptp_rule_proxy.PTPProxyMain
-Dlocal.port=1234

Test if starts:
wget localhost:1234

Encoded page example:
http://stats.grok.se/
-Dlocal.port=1235 -Dremote.host=stats.grok.se

or

-Dlocal.port=1235 -Dremote.host=en.wikipedia.org
wget localhost:1234/wiki/Small

