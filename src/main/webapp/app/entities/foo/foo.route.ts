import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { FooComponent } from './foo.component';
import { FooDetailComponent } from './foo-detail.component';
import { FooPopupComponent } from './foo-dialog.component';
import { FooDeletePopupComponent } from './foo-delete-dialog.component';

@Injectable()
export class FooResolvePagingParams implements Resolve<any> {

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

export const fooRoute: Routes = [
    {
        path: 'foo',
        component: FooComponent,
        resolve: {
            'pagingParams': FooResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Foos'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'foo/:id',
        component: FooDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Foos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fooPopupRoute: Routes = [
    {
        path: 'foo-new',
        component: FooPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Foos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'foo/:id/edit',
        component: FooPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Foos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'foo/:id/delete',
        component: FooDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Foos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
