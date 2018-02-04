import { BaseEntity } from './../../shared';

export class Foo implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public age?: number,
    ) {
    }
}
