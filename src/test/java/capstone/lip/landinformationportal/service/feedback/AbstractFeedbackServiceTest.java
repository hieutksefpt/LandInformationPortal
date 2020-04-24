/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.feedback;

import capstone.lip.landinformationportal.business.repository.FeedbackRepository;
import capstone.lip.landinformationportal.business.repository.UserRepository;
import capstone.lip.landinformationportal.business.service.FeedbackService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.entity.Feedback;
import capstone.lip.landinformationportal.common.entity.User;

import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Phong
 */
public abstract class AbstractFeedbackServiceTest extends CRUDTest {
    
    @Autowired
    protected FeedbackService instance;
    
    @Autowired
    protected FeedbackRepository repository;
    
    @Autowired
    private UserRepository userRepo;
    
    protected Feedback getSampleFeedback() {
        Feedback sampleFeedback = new Feedback()
            .setFeedBackID(DEFAULT_ID)
            .setFeedbackTitle("SAMPLE FEEDBACK")
            .setFeedbackContent("SAMPLE FEEDBACK CONTENT")
            .setFeedbackStatus("OPEN")
            .setFeedbackAdminReply("DEFAULT REPLY")
            .setUser(userRepo.findById(EXISTED_ID).get());
        return sampleFeedback;
    }
    
    protected final long TOTAL_FEEDBACK = 5L;
    protected final long TOTAL_FEEDBACK_OPEN = 4L;
    protected final long TOTAL_FEEDBACK_CLOSE = 1L;
    
    protected final String VALID_FEEDBACK_STATUS = "OPEN";
    
    protected void testInsertSuccess(Feedback result) {
        //Insert success
        if (result != null) {
            //Test exist in DB
            Optional<Feedback> actual = repository.findById(result.getFeedBackID());
            if (actual.isPresent()) {
                //Compare others
                assertEquals(true, actual.get().equals(result));
                //Test number of records is not changed
                assertEquals(TOTAL_FEEDBACK + 1, repository.count());
            } else {
                fail();
            }
        } else {
            fail();
        }
    }
    
    protected void testUpdateSuccess(Feedback result) {
        //Update success
        if (result != null) {
            //Test exist in DB
            Optional<Feedback> actual = repository.findById(result.getFeedBackID());
            if (actual.isPresent()) {
                //Compare others
                assertEquals(true, actual.get().equals(result));
                //Test number of records is not changed
                assertEquals(TOTAL_FEEDBACK, repository.count());
            } else {
                fail();
            }
        } else {
            fail();
        }
    }
    
    protected void testDeleteSuccess(boolean result, long id) {
        //Delete success
        if (result) {
            //Test exist in DB
            assertEquals(false, repository.existsById(id));
            //Test number of records is decreased by 1
            assertEquals(TOTAL_FEEDBACK - 1, repository.count());
        } else {
            fail();
        }
    }
    
    protected void testFindSuccess(long id, Feedback feedback) {
        assertEquals(repository.findById(id).get(), feedback);
    }
    
    protected void testReplySuccess(boolean result, Feedback input) {
        if (result) {
            testUpdateSuccess(input);
        } else {
            fail();
        }
    }
}
