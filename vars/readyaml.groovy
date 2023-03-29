def khaliltest(){
  def configVal = readYaml file: "datacenters.yml"
  echo "configVal: " + configVal
  echo configVal['datacenterc']["db-copy-and-restore"]["data-centers"][0]
  def asString = configVal['datacenterc']["db-copy-and-restore"]["data-centers"].join(", ")
  echo asString

}

