import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

// Set security realm (Jenkins own user database)
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("admin","AdminPassword123") // change username & password
instance.setSecurityRealm(hudsonRealm)

// Set authorization to full control once logged in
instance.setAuthorizationStrategy(new FullControlOnceLoggedInAuthorizationStrategy(true))

instance.save()

