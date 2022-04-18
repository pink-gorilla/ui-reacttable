(ns pinkgorilla.reacttable.reacttable
  (:require
   [clojure.set :refer [rename-keys]]
   [re-frame.core :as rf]
   [garden.core :refer [css]]
   ["react" :as react]
   ["react-table" :as rt :refer [useTable]] ; :default ReactTable
   [pinkie.box :refer [apply-style]]))

(defn default-column [k]
  {:headerName (name k)
   :field (name k)
   :resizable true
   :sortable true
   :filter true})

(defn default-cols [spec]
  (let [spec (rename-keys spec  {:columns :columnDefs
                                 :data :rowData})
        {:keys [columnDefs rowData]} spec]
    (if (and (not columnDefs) rowData)
      (let [row1 (first rowData)
            col-keys (keys row1)
            columns (into []
                          (map default-column col-keys))
            r (assoc spec
                     :columnDefs columns)]
        ;(println "r:" r)
        r)
      spec)))

(defn e
  [el props & children]
  (apply (.-createElement react) el (clj->js props) children))

(defn e-js
  [el props-js & children]
  (apply (.-createElement react) el props-js children))

(defn get-props [props]
  (let [columns-js (.-columns props)
        data-js (.-data props)
        ;_ (println "data-js: " data-js "columns: " columns-js)
        ;_ (.log js/console columns-js)
        ;_ (.log js/console data-js)
        columns-js-memo (.useMemo react (fn [] columns-js) #js [])
        data-js-memo (.useMemo react (fn [] data-js) #js [])
        props-js  (clj->js {"columns" columns-js-memo "data" data-js-memo})]
    props-js))

; perhaps use styled-componets library:
; import styled from 'styled-components'
(def table-style
  (css [[""
         {:padding "1rem"}]

        [:table
         {:border-spacing 0
          :border "1px solid black"}
         [:tr
          [":last-child"
           [:td {:border-bottom 0}]]]
         [:th
          :td
          {:margin 0
           :padding "0.5rem"
           :border-bottom "1px solid black"
           :border-right "1px solid black"}
          [":last-child" {:border-right 0}]]]]))

(println table-style)

(defn ^{:category :viz}
  react-table1
  "displays a seq in a table, uses react-table"
  [p]
  ;(set! (.-c js/window) (clj->js columns))
  ;(set! (.-d js/window) (clj->js data))
  (let [;_ (.log js/console "props-js:")
        ;_ (.log js/console p)
        props-js (get-props p)
        ;_ (println "props-js: " props-js)
        _ (set! (.-p js/window) props-js)
        t (useTable props-js)
        ;_ (.log js/console t)
        header-groups (.-headerGroups t)
        rows (.-rows t)
        prepare-row (.-prepareRow t)
        header-col (fn [col]
                     ;(.log js/console "header col: " col)
                     ;(.log js/console (.getHeaderProps col))
                     (e-js "th" (.getHeaderProps col)
                           (.render col "Header")))
        header-group (fn [header-group]
                       (let [headers (.-headers header-group)]
                         ;(println "header group:")
                         ;(.log js/console headers)
                         (e-js "tr" (.getHeaderGroupProps header-group)
                               (.map headers header-col))))
        show-row-cell (fn [cell]
                        (e-js "td" (.getCellProps cell)
                              (.render cell "Cell")))
        show-row (fn [row i]
                   (prepare-row row)
                   (let [cells (.-cells row)]
                     (e-js "tr" (.getRowProps row)
                           (.map cells show-row-cell))))]
    (e-js "table" (.getTableProps t)
          (e-js "thead" nil
                (.map header-groups header-group))
          (e-js "tbody" (.getTableBodyProps t)
                (.map rows show-row)))))

(defn example1
  []
  (let [[count setCount] (.useState react 0)]
    (e "div" nil
       (e "p" nil "You clicked " count " times")
       (e "button"
          {:onClick
           (fn [e]
             (setCount (inc count)))}
          "Click Me"))))

(defn example []
  (e example1 nil))

(defn react-table [props]
  [:<>
   [:style table-style]
   [:div
     ;[:div "props: " (pr-str props)]
    (e react-table1 props)]])