def khaliltest(){
  def configVal = readYaml file: "datacenters.yml"
  def asString = configVal['datacenters']["db-copy-and-restore"]["data-centers"].join(", ")
  echo asString

}

