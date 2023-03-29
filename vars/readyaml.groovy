def get_data_centers(jobname){
  def configVal = readYaml file: "datacenters.yml"
  def datacenters = configVal['datacenters']["${jobname}"]["data-centers"] 
  def x = datacenters.find{ it == "${jobname}" }?.value
  if(x)
    println "x value: ${x}"
  return datacenters
}

