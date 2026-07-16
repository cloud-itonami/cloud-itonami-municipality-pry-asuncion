(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Asunción (Municipalidad
  de Asunción, Paraguay) -- the TWENTY-FIFTH municipality-level entry
  (see cloud-itonami-municipality-jpn-tokyo, -usa-washington-dc,
  -gbr-london, -can-toronto, -deu-berlin, -fra-paris, -nld-amsterdam,
  -esp-madrid, -kor-seoul, -ita-roma, -aus-sydney, -arg-buenos-aires,
  -fin-helsinki, -dnk-copenhagen, -nor-oslo, -bel-brussels,
  -chl-santiago, -col-bogota, -cri-san-jose, -bra-sao-paulo,
  -ury-montevideo, -zaf-cape-town, -ecu-quito, -swe-gothenburg for the
  first twenty-four) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL asuncion.gov.py (Municipality of
  Asunción's own website) URL -- never fabricated. The plastic-straw
  ordinance's precise adoption date (2022-10-05) is WebSearch-
  corroborated since the Municipality's own article page confirms only
  the year (2022) and a six-month post-promulgation implementation
  window, not the exact day. The Directorate of Transparency and
  Anti-Corruption's creation date (2017-11-23) was directly confirmed
  on the Municipality's own announcement page. An ordinance not in
  this table has NO spec-basis, full stop; extend `catalog`, do not
  invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"asuncion"
   [{:ordinance/id "asuncion.ordenanza-43-2022-pajitas-plastico"
     :ordinance/title "Ordenanza N.º 43/22 (Prohíbe la Utilización, Entrega y Expendio de Pajitas de Plástico)"
     :ordinance/municipality "asuncion"
     :ordinance/country "PRY"
     :ordinance/kind :ordinance
     :ordinance/number "Ordenanza N.º 43/22"
     :ordinance/url "https://www.asuncion.gov.py/area-social/defensa-al-consumidor/municipalidad-controlara-el-cumplimiento-de-la-ordenanza-que-establece-la-no-utilizacion-de-pajitas-de-plastico-y-multara-a-los-que-la-incumplan"
     :ordinance/url-provenance :official-asuncion-gov-py
     :ordinance/enacted-date "2022-10-05"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:environment}}
    {:ordinance/id "asuncion.direccion-transparencia-anticorrupcion"
     :ordinance/title "Creación de la Dirección de Transparencia y Anticorrupción"
     :ordinance/municipality "asuncion"
     :ordinance/country "PRY"
     :ordinance/kind :ordinance
     :ordinance/url "https://www.asuncion.gov.py/intendencia/municipalidad-asuncion-cuenta-una-nueva-direccion-velara-la-transparencia-anticorrupcion"
     :ordinance/url-provenance :official-asuncion-gov-py
     :ordinance/enacted-date "2017-11-23"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:governance :transparency}}]})

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
      :note (str "cloud-itonami-municipality-pry-asuncion Wave 0 (ADR-2607141700): "
                 (count (get catalog "asuncion")) " Asunción entries seeded "
                 "with an official asuncion.gov.py citation. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
