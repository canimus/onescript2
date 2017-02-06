Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.hostname = "testhub"
  config.vm.network :private_network, ip: "192.168.33.10"
  config.vm.provider "virtualbox" do |v|
    v.memory = 8192
    v.cpus = 4
  end
end
