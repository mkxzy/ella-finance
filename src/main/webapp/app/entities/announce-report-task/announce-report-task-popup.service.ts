import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { AnnounceReportTask } from './announce-report-task.model';
import { AnnounceReportTaskService } from './announce-report-task.service';

@Injectable()
export class AnnounceReportTaskPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private announceReportTaskService: AnnounceReportTaskService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.announceReportTaskService.find(id)
                    .subscribe((announceReportTaskResponse: HttpResponse<AnnounceReportTask>) => {
                        const announceReportTask: AnnounceReportTask = announceReportTaskResponse.body;
                        this.ngbModalRef = this.announceReportTaskModalRef(component, announceReportTask);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.announceReportTaskModalRef(component, new AnnounceReportTask());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    announceReportTaskModalRef(component: Component, announceReportTask: AnnounceReportTask): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.announceReportTask = announceReportTask;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
