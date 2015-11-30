-- Function: atualizarvaloresdeextrato()

-- DROP FUNCTION atualizarvaloresdeextrato();

CREATE OR REPLACE FUNCTION atualizarvaloresdeextrato()
  RETURNS trigger AS
$BODY$
DECLARE
		valor real;
		diferenca real;
		extrato "Extrato"%rowtype;
	BEGIN
		IF(TG_OP = 'INSERT') THEN			
			SELECT * INTO extrato FROM "Extrato"
				WHERE "idConta" = NEW."idConta"
				AND "idExtrato" < NEW."idExtrato"
				ORDER BY "idExtrato" DESC
				LIMIT 1;
				
			IF(extrato IS NOT NULL)	THEN
				valor := extrato."valorFinal";
				UPDATE "Extrato"
					SET "valorInicial" = valor, "valorFinal" = valor
					WHERE "idExtrato" = NEW."idExtrato";
			END IF;

			return new;
		ELSIF(TG_OP = 'UPDATE') THEN			
			SELECT * INTO extrato FROM "Extrato"
				WHERE "idConta" = NEW."idConta"
				AND "idExtrato" > NEW."idExtrato"
				ORDER BY "idExtrato"
				LIMIT 1;

			IF(extrato IS NULL) THEN
				return new;
			END IF;
							
			valor := NEW."valorFinal";
			diferenca := extrato."valorFinal" - extrato."valorInicial";
			
			UPDATE "Extrato"
			SET "valorInicial" = valor, "valorFinal" = valor + diferenca
			WHERE "idExtrato" = extrato."idExtrato";
			
			return new;
		ELSIF(TG_OP = 'DELETE') THEN
			SELECT * INTO extrato FROM "Extrato"
				WHERE "idConta" = OLD."idConta"
				AND "idExtrato" > OLD."idExtrato"
				ORDER BY "idExtrato"
				LIMIT 1;

			IF(extrato IS NULL) THEN
				return new;
			END IF;
				
			valor := OLD."valorInicial";
			diferenca := extrato."valorFinal" - extrato."valorInicial";	
			UPDATE "Extrato"
			SET "valorInicial" = valor, "valorFinal" = valor + diferenca
			WHERE "idExtrato" = extrato."idExtrato";
			
			return old;					
		END IF;
	END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION atualizarvaloresdeextrato()
  OWNER TO postgres;

CREATE TRIGGER atualizarvaloresdeextrato
	AFTER INSERT OR UPDATE OR DELETE ON "Extrato"
	FOR EACH ROW
	EXECUTE PROCEDURE atualizarvaloresdeextrato();