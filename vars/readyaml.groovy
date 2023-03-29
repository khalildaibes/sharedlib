def khaliltest(){
  def configVal = readYaml file: "datacenters.yml"
  def datacenters = configVal['datacenters']["db-copy-and-restore"]["data-centers"]
  def datacentersasstring = configVal['datacenters']["db-copy-and-restore"]["data-centers"].join(", ")
  echo datacentersasstring
  return datacenters
}

