def get_data_centers(jobname){
  def configVal = readYaml file: "datacenters.yml"
  echo configVal['datacenters']
  echo jobname
  if (jobname in configVal['datacenters'])){
    def datacenters = configVal['datacenters']["${jobname}"]["data-centers"]
  }else{
    echo "Job isn't supported"
    return [""]
  }
  return datacenters
}

