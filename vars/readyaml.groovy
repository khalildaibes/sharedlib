def khaliltest(){
  def configVal = readYaml file: "datacenters.yml"
  def asString = configVal['datacenterc']["db-copy-and-restore"]["data-centers"].join(", ")
  echo asString

}

