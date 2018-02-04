import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AnnounceReportTask } from './announce-report-task.model';
import { AnnounceReportTaskPopupService } from './announce-report-task-popup.service';
import { AnnounceReportTaskService } from './announce-report-task.service';

@Component({
    selector: 'jhi-announce-report-task-delete-dialog',
    templateUrl: './announce-report-task-delete-dialog.component.html'
})
export class AnnounceReportTaskDeleteDialogComponent {

    announceReportTask: AnnounceReportTask;

    constructor(
        private announceReportTaskService: AnnounceReportTaskService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.announceReportTaskService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'announceReportTaskListModification',
                content: 'Deleted an announceReportTask'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-announce-report-task-delete-popup',
    template: ''
})
export class AnnounceReportTaskDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private announceReportTaskPopupService: AnnounceReportTaskPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.announceReportTaskPopupService
                .open(AnnounceReportTaskDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
