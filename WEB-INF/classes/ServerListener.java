

import java.util.GregorianCalendar;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.theOasis.controller.BBSController;
import com.theOasis.controller.GroupController;
import com.theOasis.controller.LanguageBuddyController;
import com.theOasis.controller.MemberController;
import com.theOasis.controller.MessageController;
import com.theOasis.controller.WebHardController;
import com.theOasis.dao.impl.OasisDAOManagement;
import com.theOasis.member.TheOasisMember;
import com.theOasis.profileIO.ProfileIO;
import com.theOasis.text.bbs.BBSManagement;

/**
 * Application Lifecycle Listener implementation class ServerListener
 *
 */
public class ServerListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ServerListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        try {
			OasisDAOManagement oasis = new OasisDAOManagement();
			oasis.connect();
			oasis.select();
			
			BBSController.getInstance().setManager(oasis.getTranslator().getBbsM());
			GroupController.getInstance().setManager(oasis.getTranslator().getGroupM());
			LanguageBuddyController.getInstance().setManager(oasis.getTranslator().getLanguageBuddyM());
			MemberController.getInstance().setManager(oasis.getTranslator().getMemberM());
			MessageController.getInstance().setManager(oasis.getTranslator().getMessageM());
			WebHardController.getInstance().setManager(oasis.getTranslator().getWebhardM());
			ProfileIO.importProfile();
			oasis.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        try {

        	OasisDAOManagement oasis = new OasisDAOManagement(); 	
			oasis.connect();
			oasis.getTranslator().setBbsM(BBSController.getInstance().getManager());
			oasis.getTranslator().setGroupM(GroupController.getInstance().getManager());
			oasis.getTranslator().setLanguageBuddyM(LanguageBuddyController.getInstance().getManager());
			oasis.getTranslator().setMemberM(MemberController.getInstance().getManager());
			oasis.getTranslator().setMessageM(MessageController.getInstance().getManager());
			oasis.getTranslator().setWebhardM(WebHardController.getInstance().getManager());
			ProfileIO.exportProfile();
			oasis.delete();
			oasis.insert();
			oasis.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
	
}
