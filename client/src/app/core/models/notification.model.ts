import { User } from ".";

export interface Notification {
    id?: number;
    recipientId: number;
    content: string;
    dateAdded?: string;
    type: string;
    sender: User;
    read?: boolean;
}
  