import { OperationType } from './operation-type';

export interface Operation {
  id: string;
  date: Date;
  amount: number;
  type: OperationType;
  description: string;
}
