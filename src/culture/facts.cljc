(ns culture.facts
  "Regional-culture catalog for Asunción -- local dishes, protected
  products, beverages, festivals and heritage sites, piggybacked onto
  this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"asuncion"
   [{:culture/id "asuncion.dish.sopa-paraguaya"
     :culture/name "Sopa paraguaya"
     :culture/municipality "asuncion"
     :culture/country "PRY"
     :culture/kind :dish
     :culture/summary "Cornbread-like traditional food of Paraguayan cuisine, made with corn flour, cheese, eggs and milk."
     :culture/url "https://en.wikipedia.org/wiki/Sopa_paraguaya"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "asuncion.dish.chipa"
     :culture/name "Chipa"
     :culture/municipality "asuncion"
     :culture/country "PRY"
     :culture/kind :dish
     :culture/summary "Cheese-flavored snack and breakfast bread popular in Paraguay; its name comes from Guaraní and its origins trace to the Guaraní people of Asunción."
     :culture/url "https://en.wikipedia.org/wiki/Chipa"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "asuncion.dish.chipa-guasu"
     :culture/name "Chipa guasu"
     :culture/municipality "asuncion"
     :culture/country "PRY"
     :culture/kind :dish
     :culture/summary "Cake made with maize kernels, onions and Paraguayan cheese, from Paraguay."
     :culture/url "https://en.wikipedia.org/wiki/Chipa_guasu"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "asuncion.dish.mbeju"
     :culture/name "Mbeju"
     :culture/name-local "Mbejú"
     :culture/municipality "asuncion"
     :culture/country "PRY"
     :culture/kind :dish
     :culture/summary "Starch cake typical of Paraguay; the Guaraní name mbejú means cake."
     :culture/url "https://en.wikipedia.org/wiki/Mbeju"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "asuncion.dish.vori-vori"
     :culture/name "Vori vori"
     :culture/municipality "asuncion"
     :culture/country "PRY"
     :culture/kind :dish
     :culture/summary "Thick, yellowish soup with little balls of corn flour and cheese, a traditional dish of Paraguayan cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Vori_vori"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "asuncion.beverage.terere"
     :culture/name "Tereré"
     :culture/municipality "asuncion"
     :culture/country "PRY"
     :culture/kind :beverage
     :culture/summary "Infusion of yerba mate prepared with cold water, ice and medicinal herbs, of Guaraní origin; recognized by UNESCO as intangible cultural heritage in 2020."
     :culture/url "https://en.wikipedia.org/wiki/Terer%C3%A9"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "asuncion.craft.nanduti"
     :culture/name "Ñandutí"
     :culture/municipality "asuncion"
     :culture/country "PRY"
     :culture/kind :craft
     :culture/summary "Traditional Paraguayan needle lace made with cotton or silk threads; the Guaraní name means spider web."
     :culture/url "https://en.wikipedia.org/wiki/%C3%91andut%C3%AD"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "asuncion.heritage.national-pantheon-of-the-heroes"
     :culture/name "National Pantheon of the Heroes"
     :culture/name-local "Panteón Nacional de los Héroes"
     :culture/municipality "asuncion"
     :culture/country "PRY"
     :culture/kind :heritage
     :culture/summary "Building and landmark of Asunción and a national monument of Paraguay."
     :culture/url "https://en.wikipedia.org/wiki/National_Pantheon_of_the_Heroes"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "asuncion.heritage.palacio-de-los-lopez"
     :culture/name "Palacio de los López"
     :culture/municipality "asuncion"
     :culture/country "PRY"
     :culture/kind :heritage
     :culture/summary "Palace in Asunción serving as a workplace for the President of Paraguay and as the seat of the government of Paraguay."
     :culture/url "https://en.wikipedia.org/wiki/Palacio_de_los_L%C3%B3pez"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-pry-asuncion culture catalog "
                 "(ADR-2607171400): " (count (get catalog "asuncion"))
                 " Asunción entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
