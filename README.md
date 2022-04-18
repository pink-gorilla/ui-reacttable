# ui-reacttable [![GitHub Actions status |pink-gorilla/ui-aggrid](https://github.com/pink-gorilla/ui-aggrid/workflows/CI/badge.svg)](https://github.com/pink-gorilla/ui-aggrid/actions?workflow=CI)[![Clojars Project](https://img.shields.io/clojars/v/org.pinkgorilla/ui-aggrid.svg)](https://clojars.org/org.pinkgorilla/ui-aggrid)

https://react-table-v7.tanstack.com/docs/examples/footers

## Demo

```
clojure -X:goldly
```

Navigate your webbrowser to port 8080. 

## In Goldly as a ui extension

In deps.edn add ui-reacttable as dependency and add goldly alias

```
:goldly
  {:extra-deps {org.pinkgorilla/goldly {:mvn/version "RELEASE"}
               {org.pinkgorilla/ui-reacttable {:mvn/version "0.0.2"}}
   :exec-fn goldly-server.app/goldly-server-run!
   :exec-args {:profile "watch"
               :config {:goldly {}}}}
```




