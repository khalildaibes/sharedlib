def khaliltest(){
  def configVal = readYaml file: "datacenters.yml"
  echo "configVal: " + configVal
  echo configVal['datacenterc']["track3-migration-master"]["data-centers"]
}

