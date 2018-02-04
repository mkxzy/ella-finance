/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EllaFinanceTestModule } from '../../../test.module';
import { AnnounceReportTaskComponent } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task.component';
import { AnnounceReportTaskService } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task.service';
import { AnnounceReportTask } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task.model';

describe('Component Tests', () => {

    describe('AnnounceReportTask Management Component', () => {
        let comp: AnnounceReportTaskComponent;
        let fixture: ComponentFixture<AnnounceReportTaskComponent>;
        let service: AnnounceReportTaskService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EllaFinanceTestModule],
                declarations: [AnnounceReportTaskComponent],
                providers: [
                    AnnounceReportTaskService
                ]
            })
            .overrideTemplate(AnnounceReportTaskComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AnnounceReportTaskComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AnnounceReportTaskService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new AnnounceReportTask('123')],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.announceReportTasks[0]).toEqual(jasmine.objectContaining({id: '123'}));
            });
        });
    });

});
