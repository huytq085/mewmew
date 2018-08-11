import { User } from ".";

export interface Notification {
    id: number;
    username: string;
    content: string;
    dateAdded: string;
    type: string;
    sender: User;
}
  