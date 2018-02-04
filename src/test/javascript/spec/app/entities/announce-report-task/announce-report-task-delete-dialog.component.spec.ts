/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { EllaFinanceTestModule } from '../../../test.module';
import { AnnounceReportTaskDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task-delete-dialog.component';
import { AnnounceReportTaskService } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task.service';

describe('Component Tests', () => {

    describe('AnnounceReportTask Management Delete Component', () => {
        let comp: AnnounceReportTaskDeleteDialogComponent;
        let fixture: ComponentFixture<AnnounceReportTaskDeleteDialogComponent>;
        let service: AnnounceReportTaskService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EllaFinanceTestModule],
                declarations: [AnnounceReportTaskDeleteDialogComponent],
                providers: [
                    AnnounceReportTaskService
                ]
            })
            .overrideTemplate(AnnounceReportTaskDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AnnounceReportTaskDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AnnounceReportTaskService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete('123');
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith('123');
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
