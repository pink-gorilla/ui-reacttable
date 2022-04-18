# ui-reacttable [![GitHub Actions status |pink-gorilla/ui-reacttable](https://github.com/pink-gorilla/ui-reacttable/workflows/CI/badge.svg)](https://github.com/pink-gorilla/ui-reacttable/actions?workflow=CI)[![Clojars Project](https://img.shields.io/clojars/v/org.pinkgorilla/ui-reacttable.svg)](https://clojars.org/org.pinkgorilla/ui-reacttable)

https://react-table-v7.tanstack.com/docs/examples/footers


The old api is implemented also here:
https://github.com/alex314159/react-table-vega-template/blob/master/src/main/reacttabledefinition.cljs

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




