IF NOT EXISTS (
    SELECT  1
    FROM    sys.schemas
    WHERE   name = N 'reclamos'
)
    EXEC('CREATE SCHEMA [reclamos]');
GO

