def khaliltest(jobname){
  def configVal = readYaml file: "datacenters.yml"
  def datacenters = configVal['datacenters']["${jobname}"]["data-centers"]
  return datacenters
}

