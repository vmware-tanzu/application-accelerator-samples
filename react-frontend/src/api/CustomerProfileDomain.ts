export class CustomerProfile {
  constructor(
    public id: string | null,
    public firstName: string | null,
    public lastName: string | null,
    public email: string | null
  ) {
  }

  valid(): boolean {
    return (this.email !== null && this.email !== '')
  }
}
