def get_data_centers(jobname){
  def configVal = readYaml file: "datacenters.yml"
  echo configVal['datacenters'].getClass().toString()
  HashMap h = new HashMap(configVal);
  if (configVal['datacenters'].get(jobname.toString())){
  def datacenters = configVal['datacenters']["${jobname}"]["data-centers"]
  }
      else {
        echo "blah blah"
      }
  return datacenters
}

