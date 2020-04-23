/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.report;

import capstone.lip.landinformationportal.business.repository.ReportRepository;
import capstone.lip.landinformationportal.business.service.ReportService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.entity.Report;
import capstone.lip.landinformationportal.common.entity.compositekey.ReportId;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Phong
 */
public abstract class AbstractReportServiceTest extends CRUDTest {

    @Autowired
    protected ReportService instance;

    @Autowired
    protected ReportRepository repository;

    protected Report sampleReport = new Report()
            .setId(new ReportId()
                    .setRealestateId(DEFAULT_ID)
                    .setUserId(DEFAULT_ID));

    protected final long TOTAL_REPORT = 5L;
    
    protected void testUpdateSuccess(Report result) {
        //Update success
        if (result != null) {
            Report actual = repository
                    .findByIdUserIdAndIdRealEstateId(
                            result.getId().getRealestateId(),
                            result.getId().getUserId());
            //Compare others
            assertEquals(true, actual.equals(result));
            //Test number of records is not changed
            assertEquals(TOTAL_REPORT, repository.count());
        } else {
            fail();
        }
    }

    protected void testDeleteSuccess(boolean result, Report detail) {
        //Delete success
        if (result) {
            //Test exist in DB
            assertEquals(null, repository
                    .findByIdUserIdAndIdRealEstateId(
                            detail.getId().getRealestateId(),
                            detail.getId().getUserId()));
            //Test number of records is decreased by 1
            assertEquals(TOTAL_REPORT - 1, repository.count());
        } else {
            fail();
        }
    }
    
    protected void testFindSuccess(Report result, long userId, long realEstateId) {
        assertEquals(repository
                .findByIdUserIdAndIdRealEstateId(userId, realEstateId), 
                result);
    }
}
