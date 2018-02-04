/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { EllaFinanceTestModule } from '../../../test.module';
import { AnnounceReportTaskDialogComponent } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task-dialog.component';
import { AnnounceReportTaskService } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task.service';
import { AnnounceReportTask } from '../../../../../../main/webapp/app/entities/announce-report-task/announce-report-task.model';

describe('Component Tests', () => {

    describe('AnnounceReportTask Management Dialog Component', () => {
        let comp: AnnounceReportTaskDialogComponent;
        let fixture: ComponentFixture<AnnounceReportTaskDialogComponent>;
        let service: AnnounceReportTaskService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EllaFinanceTestModule],
                declarations: [AnnounceReportTaskDialogComponent],
                providers: [
                    AnnounceReportTaskService
                ]
            })
            .overrideTemplate(AnnounceReportTaskDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AnnounceReportTaskDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AnnounceReportTaskService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AnnounceReportTask('123');
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.announceReportTask = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'announceReportTaskListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AnnounceReportTask();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.announceReportTask = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'announceReportTaskListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
