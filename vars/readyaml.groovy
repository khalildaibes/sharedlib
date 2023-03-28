def khaliltest(){
  scriptFile = getClass().protectionDomain.codeSource.location.path
  echo "${scriptFile}"
  echo "${workspace}"
}

