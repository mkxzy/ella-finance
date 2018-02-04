import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { AnnounceReportTaskComponent } from './announce-report-task.component';
import { AnnounceReportTaskDetailComponent } from './announce-report-task-detail.component';
import { AnnounceReportTaskPopupComponent } from './announce-report-task-dialog.component';
import { AnnounceReportTaskDeletePopupComponent } from './announce-report-task-delete-dialog.component';

@Injectable()
export class AnnounceReportTaskResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const announceReportTaskRoute: Routes = [
    {
        path: 'announce-report-task',
        component: AnnounceReportTaskComponent,
        resolve: {
            'pagingParams': AnnounceReportTaskResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AnnounceReportTasks'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'announce-report-task/:id',
        component: AnnounceReportTaskDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AnnounceReportTasks'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const announceReportTaskPopupRoute: Routes = [
    {
        path: 'announce-report-task-new',
        component: AnnounceReportTaskPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AnnounceReportTasks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'announce-report-task/:id/edit',
        component: AnnounceReportTaskPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AnnounceReportTasks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'announce-report-task/:id/delete',
        component: AnnounceReportTaskDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AnnounceReportTasks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
