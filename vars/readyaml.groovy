def khaliltest(jobname){
  def configVal = readYaml file: "datacenters.yml"
  def datacenters = configVal['datacenters']["${jobname}"]["data-centers"]
  def datacentersasstring = configVal['datacenters']["db-copy-and-restore"]["data-centers"].join(", ")
  echo datacentersasstring
  return datacenters
}

