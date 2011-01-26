

package bootstrap.liftweb

import net.liftweb._
import http._
import sitemap.{SiteMap, Menu, Loc}
import Loc._
import util.{ Helpers }
import Helpers._


import xml._

class Boot {
  def boot {
  
    var count = 0
    
    // build sitemap
    val entries = 
      List(
        Menu("Home") / "index" >> Template(() => {        
        <lift:surround with="default" at="content">
          <lift:demo form="post">
          Click a button multiple times within 5 seconds
            <demo:callOnce/>
            <demo:oneShot/>
          </lift:demo>
        </lift:surround>                
        }) >> Snippet("demo", xhtml => {
          
          def fire = {
            count += 1
            Thread.sleep(5000)
            S.redirectTo("/result")
          }
          
          bind("demo", xhtml, 
            "callOnce" -> S.callOnce(SHtml.submit("Call Once", fire _)), 
            "oneShot" -> S.oneShot(SHtml.submit("One Shot", fire _)))
        }),
        Menu("Result") / "result" >> Template(() => {
          <lift:surround with="default" at="content">
            { count }
          </lift:surround>
        }))
      
    LiftRules.setSiteMap(SiteMap(entries:_*))    
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))    
  }
}