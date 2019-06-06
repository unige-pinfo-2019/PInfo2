export interface AdQuery {
    query: string,
    filters : {
        userId?: string,
        categoryId?: number
    }
}