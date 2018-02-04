/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { EllaFinanceTestModule } from '../../../test.module';
import { AnnounceReportTaskDetailComponent } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task-detail.component';
import { AnnounceReportTaskService } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task.service';
import { AnnounceReportTask } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task.model';

describe('Component Tests', () => {

    describe('AnnounceReportTask Management Detail Component', () => {
        let comp: AnnounceReportTaskDetailComponent;
        let fixture: ComponentFixture<AnnounceReportTaskDetailComponent>;
        let service: AnnounceReportTaskService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EllaFinanceTestModule],
                declarations: [AnnounceReportTaskDetailComponent],
                providers: [
                    AnnounceReportTaskService
                ]
            })
            .overrideTemplate(AnnounceReportTaskDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AnnounceReportTaskDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AnnounceReportTaskService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new AnnounceReportTask('123')
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith('123');
                expect(comp.announceReportTask).toEqual(jasmine.objectContaining({id: '123'}));
            });
        });
    });

});
